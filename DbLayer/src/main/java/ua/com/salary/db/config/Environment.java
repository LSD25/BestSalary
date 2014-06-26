package ua.com.salary.db.config;

/**
 * @author Victor Zagnitko on 17.06.2014.
 */
public enum Environment {

    PRODUCTION("PROD"),
    DEVELOPMNET("DEV"),
    LOCALHOST("LOCAL");

    private String mEnv;

    Environment(String env) {
        this.mEnv = env;
    }

    public String getEnv() {
        return this.mEnv;
    }

}
