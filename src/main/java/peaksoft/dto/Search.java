package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Search {
    private String search;

    public Search() {
    }

    public Search(String search) {
        this.search = search;
    }
}
