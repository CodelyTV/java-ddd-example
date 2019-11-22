package tv.codely.mooc.courses.application.search_last;

import tv.codely.mooc.courses.application.CoursesResponse;
import tv.codely.shared.domain.Service;
import tv.codely.shared.domain.bus.query.QueryHandler;

@Service
public final class SearchLastCoursesQueryHandler implements QueryHandler<SearchLastCoursesQuery, CoursesResponse> {
    private final LastCoursesSearcher searcher;

    public SearchLastCoursesQueryHandler(LastCoursesSearcher searcher) {
        this.searcher = searcher;
    }

    @Override
    public CoursesResponse handle(SearchLastCoursesQuery query) {
        return searcher.search(query.total());
    }
}
