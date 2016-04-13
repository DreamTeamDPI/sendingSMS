package com.common.filters;

/**
 * Created by SemmEs on 13.04.2016.
 */
public class FilterPaymentStatistic {

    String dateBegin;
    String dateEnd;

    public FilterPaymentStatistic() {
    }

    public FilterPaymentStatistic(String dateBegin, String dateEnd) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public String getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(String dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return "FilterPaymentStatistic{" +
                "dateBegin='" + dateBegin + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                '}';
    }
}
