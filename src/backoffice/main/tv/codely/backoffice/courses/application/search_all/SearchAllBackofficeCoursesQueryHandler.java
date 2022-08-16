package tv.codely.backoffice.courses.application.search_all;

import tv.codely.backoffice.courses.application.BackofficeCoursesResponse;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchAllBackofficeCoursesQueryHandler implements QueryHandler<SearchAllBackofficeCoursesQuery, BackofficeCoursesResponse> {
    private final AllBackofficeCoursesSearcher searcher;

    public SearchAllBackofficeCoursesQueryHandler(AllBackofficeCoursesSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public BackofficeCoursesResponse handle(SearchAllBackofficeCoursesQuery query) {
        return searcher.search();
    }
}
