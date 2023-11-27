package tv.codely.shared.infrastructure.bus.event.mysql;

import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Qualifier;
import tv.codely.shared.domain.Utils;
import tv.codely.shared.domain.bus.event.DomainEvent;
import tv.codely.shared.domain.bus.event.EventBus;
import tv.codely.shared.infrastructure.bus.event.DomainEventsInformation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MySqlDomainEventsConsumer {
	private final SessionFactory sessionFactory;
	private final DomainEventsInformation domainEventsInformation;
	private final EventBus bus;
	private final Integer CHUNKS = 200;
	private Boolean shouldStop = false;

	public MySqlDomainEventsConsumer(
		@Qualifier("mooc-session_factory") SessionFactory sessionFactory,
		DomainEventsInformation domainEventsInformation,
		EventBus bus
	) {
		this.sessionFactory = sessionFactory;
		this.domainEventsInformation = domainEventsInformation;
		this.bus = bus;
	}

	@Transactional
	public void consume() {
		while (!shouldStop) {
			NativeQuery query = sessionFactory.getCurrentSession().createNativeQuery(
				"SELECT * FROM domain_events ORDER BY occurred_on ASC LIMIT :chunk"
			);

			query.setParameter("chunk", CHUNKS);

			List<Object[]> events = query.list();

			try {
				for (Object[] event : events) {
					executeSubscribers(
						(String) event[0],
						(String) event[1],
						(String) event[2],
						(String) event[3],
						(Timestamp) event[4]
					);
				}
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException |
					 InstantiationException e) {
				e.printStackTrace();
			}

			sessionFactory.getCurrentSession().clear();
		}
	}

	public void stop() {
		shouldStop = true;
	}

	private void executeSubscribers(
		String id, String aggregateId, String eventName, String body, Timestamp occurredOn
	) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Class<? extends DomainEvent> domainEventClass = domainEventsInformation.forName(eventName);

		DomainEvent nullInstance = domainEventClass.getConstructor().newInstance();

		Method fromPrimitivesMethod = domainEventClass.getMethod(
			"fromPrimitives",
			String.class,
			HashMap.class,
			String.class,
			String.class
		);

		Object domainEvent = fromPrimitivesMethod.invoke(
			nullInstance,
			aggregateId,
			Utils.jsonDecode(body),
			id,
			Utils.dateToString(occurredOn)
		);

		bus.publish(Collections.singletonList((DomainEvent) domainEvent));
	}
}
