public enum Locale {
    US("en-us"),
    DE("de");

    Locale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    private String locale;


}
