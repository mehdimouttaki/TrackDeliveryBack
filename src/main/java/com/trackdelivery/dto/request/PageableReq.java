package com.trackdelivery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageableReq {
    /**
     * This DTO represents Pagination object
     */

        /**
         * page number to  retrieve
         */
        private Integer pageNum;
        /**
         * number of records to be retrieved from {@link #pageNum}
         */
        private Integer pageSize;
        /**
         * sort field
         */
        private String sortBy;
        /**
         * sort order asc or desc
         */
        private Integer sortOrder;
        /**
         * this Boolean indicates if we should use pagination or not
         */
        private Boolean isPageable;



    }



