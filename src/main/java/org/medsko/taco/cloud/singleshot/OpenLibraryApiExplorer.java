package org.medsko.taco.cloud.singleshot;

import org.medsko.taco.cloud.client.OpenLibraryClient;
import org.medsko.taco.cloud.domain.Book;
import org.medsko.taco.cloud.domain.BookSearchCriterion;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OpenLibraryApiExplorer implements ApplicationRunner {

    private final static String TEST_ISBN = "9788025122853";

    private final OpenLibraryClient client;

    public OpenLibraryApiExplorer(OpenLibraryClient client) {
        this.client = client;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        client.searchForCleanCode();
    }

    private void findSpecificBook() {
        BookSearchCriterion searchCriterion = new BookSearchCriterion();
        searchCriterion.setValue("0201558025");
        searchCriterion.setFormat(BookSearchCriterion.Format.ISBN);

        Book test = new Book();
        test.getSearchCriteria().add(searchCriterion);

        Book result = client.getBook(test);
        System.out.println("The author of the requested book is: " + result.getAuthor());
    }
}
