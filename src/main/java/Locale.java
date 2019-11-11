public enum Locale {
    EN_US("en-us"),
    EN_DE("en-de"),
    DE_DE("de-de"),
    DE_AT("de-at"),
    EN_AT("en-at"),
    EN_DK("en-dk"),
    FR_FR("fr-fr"),
    EN_FR("en-fr"),
    IT_IT("it-it"),
    EN_IT("en-it");

    Locale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    private String locale;


}
