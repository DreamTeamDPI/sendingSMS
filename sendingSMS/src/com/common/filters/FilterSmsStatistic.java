package com.common.filters;

/**
 * Created by SemmEs on 03.04.2016.
 */
public class FilterSmsStatistic {

    private String dateBegin;
    private String dateEnd;
    private String phone;
    private int status;


    public FilterSmsStatistic() {
    }

    public FilterSmsStatistic(String dateBegin, String dateEnd, int status, String phone) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.status = status;
        this.phone = phone;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FilterUserStatistic{" +
                "dateBegin='" + dateBegin + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
