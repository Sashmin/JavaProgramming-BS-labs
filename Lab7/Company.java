public class Company {

    public static class MyDate {
        private static int[] maxMonthDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private int date;
        private int month;
        private int year;

        public MyDate(String str) throws Exception{
            if (!str.matches("\\d{2}\\.\\d{2}\\.\\d+")) {
                throw new Exception("invalid format");
            }

            date = Integer.parseInt(str.substring(0, 2));
            month = Integer.parseInt(str.substring(3, 5));
            year = Integer.parseInt(str.substring(6));
            if (year < 0 || year == 2020) {     //it's a joke...)
                throw new Exception("negative year");
            }
            if (month < 1 || month > 12) {
                throw new Exception("invalid month");
            }
            if (date < 1 || date > maxMonthDate[month - 1]) {
                throw new Exception("invalid date");
            }
        }

        public MyDate(MyDate other) {
            date = other.date;
            month = other.month;
            year = other.year;
        }

        @Override
        public String toString() {
            String result = "";
            if (date < 10) {result += "0";}
            result += date + ".";

            if (month < 10) {result += "0";}
            result += month + ".";

            result += year;
            return result;
        }

        public int compareTo(MyDate other) {
            int yearDiff = year - other.year;
            if (yearDiff != 0) {
                return yearDiff;
            }
            int monthDiff = month - other.month;
            if (monthDiff != 0) {
                return monthDiff;
            }
            return date - other.date;
        }
    }
    private String name;
    private String shortTitle;
    private MyDate myDateUpdate;
    private String address;
    private MyDate myDateFoundation;
    private int countEmployees;
    private String auditor;
    private String phone;
    private String email;
    private String branch;
    private String activity;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public MyDate getDateUpdate() {
        return myDateUpdate;
    }

    public void setDateUpdate(MyDate myDateUpdate) {
        this.myDateUpdate = myDateUpdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public MyDate getDateFoundation() {
        return myDateFoundation;
    }

    public void setDateFoundation(MyDate myDateFoundation) {
        this.myDateFoundation = myDateFoundation;
    }

    public int getCountEmployees() {
        return countEmployees;
    }

    public void setCountEmployees(int countEmployees) {
        this.countEmployees = countEmployees;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Company(String str) throws Exception{
        String[] fields = str.split(";", -1);
        if (fields.length != 12) {
            throw new Exception("invalid tokens amount in a string");
        }
        if (fields[1].equals("") || fields[4].equals("")
            || fields[5].equals("") || fields[9].equals("")
            || fields[10].equals("")) {
            throw new Exception("obligatory field not specified");
        }
        name = fields[0];
        shortTitle = fields[1];
        myDateUpdate = null;
        if (!fields[2].equals("")) {
            myDateUpdate = new MyDate(fields[2]);
        }
        address = fields[3];
        myDateFoundation = new MyDate(fields[4]);
        countEmployees = Integer.parseInt(fields[5]);
        auditor = fields[6];
        phone = fields[7];
        email = fields[8];
        branch = fields[9];
        activity = fields[10];
        link = fields[11];
    }

    public Company(Company other) {
        name = other.name;
        shortTitle = other.shortTitle;
        if (other.myDateUpdate != null) {
            myDateUpdate = new MyDate(other.myDateUpdate);
        }
        else {
            myDateUpdate = null;
        }
        address = other.address;
        myDateFoundation = new MyDate(other.myDateFoundation);
        countEmployees = other.countEmployees;
        auditor = other.auditor;
        phone = other.phone;
        email = other.email;
        branch = other.branch;
        activity = other.activity;
        link = other.link;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", shortTitle='" + shortTitle + '\'' +
                ", dateUpdate='" + myDateUpdate + '\'' +
                ", address='" + address + '\'' +
                ", dateFoundation='" + myDateFoundation + '\'' +
                ", countEmployees=" + countEmployees +
                ", auditor='" + auditor + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", branch='" + branch + '\'' +
                ", activity='" + activity + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public static void main(String[] args) throws Exception{

    }
}
