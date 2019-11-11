public enum Locale {
    DE_DE("de-de"),
    EN_DE("en-de"),
    DE_AT("de-at"),
    EN_AT("en-at"),
    EN_DK("en-dk"),
    FR_FR("fr-fr"),
    EN_FR("en-fr"),
    IT_IT("it-it"),
    EN_IT("en-it"),
    EN_NL("en-nl"),
    RU_RU("ru-ru"),
    EN_RU("en-ru"),
    ES_ES("es-es"),
    EN_ES("en-es"),
    EN_SE("en-se"),
    DE_CH("de-ch"),
    IT_CH("it-ch"),
    FR_CH("fr-ch"),
    EN_CH("en-ch"),
    EN_TR("en-tr"),
    EN_GB("en-gb"),
    ZH_CN("zh-cn"),
    EN_CN("en-cn"),
    EN_HK("en-hk"),
    EN_IN("en-in"),
    EN_ID("en-id"),
    EN_MY("en-my"),
    EN_PH("en-ph"),
    EN_SG("en-sg"),
    JA_JP("ja-jp"),
    EN_JP("en-jp"),
    KO_KR("ko-kr"),
    EN_KR("en-kr"),
    EN_TW("en-tw"),
    EN_TH("en-th"),
    ES_AR("es-ar"),
    EN_AR("en-ar"),
    PT_BR("pt-br"),
    EN_BR("en-br"),
    EN_CL("en-cl"),
    ES_CL("es-cl"),
    ES_CO("es-co"),
    EN_CO("en-co"),
    ES_PE("es-pe"),
    EN_PE("en-pe"),
    EN_CA("en-ca"),
    FR_CA("fr-ca"),
    ES_MX("es-mx"),
    EN_MX("en-mx"),
    EN_AU("en-au"),
    EN_NZ("en-nz");


    Locale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    private String locale;


}
