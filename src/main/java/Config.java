/**
 * Created by Elli on 3/13/17.
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Config allows for a global config of the toolkit. Central location for all
 * toolkit configuration defaults. Has a local config file that can override any
 * property defaults. Config file can be relocated via a system property
 * "org.hyperledger.fabric.sdk.configuration". Any property can be overridden
 * with a java system property. Property hierarchy goes System property
 * overrides config file overrides default values specified here.
 */

public class Config {

    private static final Log logger = LogFactory.getLog(Config.class);

    private static final String DEFAULT_CONFIG = "config.properties";
    private static final String ORG_HYPERLEDGER_FABRIC_SDK_CONFIGURATION = "com.atraurablockchain.configuration";
    private static final String CHAIN_ID = "com.atraurablockchain.chainid";
    private static final String CHAIN_CODE_NAME = "com.atraurablockchain.chaincodename";
    private static final String CHAIN_CODE_PATH = "com.atraurablockchain.chaincodepath";
    private static final String SOLO_ORDERER_ADDRESS = "com.atraurablockchain.ordereraddress";
    private static final String PEER_ADDRESS = "com.atraurablockchain.peeraddress";
    private static final String EVENT_ADDRESS = "com.atraurablockchain.eventhubaddress";
    private static final String CA_ADDRESS = "com.atraurablockchain.caaddress";
    private static final String USER_NAME_1 = "com.atraurablockchain.username_1";
    private static final String USER_PSWD_1 = "com.atraurablockchain.userpswd_1";
    private static final String USER_NAME_2 = "com.atraurablockchain.username_2";
    private static final String USER_PSWD_2 = "com.atraurablockchain.userpswd_2";
    private static final String KEYSTORE_PROPERTIES = "com.atraurablockchain.keystore_properties";
    private static final String DEPLOY_FUNCTION = "com.atraurablockchain.deploy_function";
    private static final String DEPLOY_ARGS = "com.atraurablockchain.deploy_args";
    private static final String INVOKE_FUNCTION = "com.atraurablockchain.invoke_function";
    private static final String INVOKE_ARGS = "com.atraurablockchain.invoke_args";
    private static final String QUERY_ARGS = "com.atraurablockchain.query_args";
    private static final String DEPLOY_WAITTIME = "com.atraurablockchain.deploy_waittime";
    private static final String INVOKE_WAITTIME = "com.atraurablockchain.invoke_waittime";

    private static Config config;
    private final static Properties properties = new Properties();
    private Config() {
        File loadFile = null;
        FileInputStream configProps;

        try {
            loadFile = new File(System.getProperty(ORG_HYPERLEDGER_FABRIC_SDK_CONFIGURATION, DEFAULT_CONFIG))
                    .getAbsoluteFile();
            logger.debug(String.format("Loading configuration from %s and it is present: %b", loadFile.toString(),
                    loadFile.exists()));
            configProps = new FileInputStream(loadFile);
            properties.load(configProps);

        } catch (IOException e) {
            logger.warn(String.format("Failed to load any configuration from: %s. Using toolkit defaults",
                    DEFAULT_CONFIG));
        } finally {

            // Default values
            // TODO remove this once we have implemented MSP and get the peer certs from the channel
            //defaultProperty(CACERTS, "/genesisblock/peercacert.pem");

        }

    }

    /**
     * getConfig return back singleton for SDK configuration.
     *
     * @return Global configuration
     */
    public static Config getConfig() {
        if (null == config) {
            config = new Config();
        }
        return config;

    }

    /**
     * getProperty return back property for the given value.
     *
     * @param property
     * @return String value for the property
     */
    private String getProperty(String property) {

        String ret = properties.getProperty(property);

        if (null == ret) {
            logger.warn(String.format("No configuration value found for '%s'", property));
        }
        return ret;
    }

    /**
     * getProperty returns the value for given property key. If not found, it
     * will set the property to defaultValue
     *
     * @param property
     * @param defaultValue
     * @return property value as a String
     */
    private String getProperty(String property, String defaultValue) {

        String ret = properties.getProperty(property, defaultValue);
        return ret;
    }

    private String[] getProperties(String property) {

        String[] ret = properties.getProperty(property).split(",");
        if (null == ret) {
            logger.warn(String.format("No configuration value found for '%s'", property));
        }
        return ret;
    }

    static private void defaultProperty(String key, String value) {

        String ret = System.getProperty(key);
        if (ret != null) {
            properties.put(key, ret);
        } else if (null == properties.getProperty(key)) {
            properties.put(key, value);
        }
    }


    /**
     * Returns chaincode name
     *
     * @return
     */
    public String getChainCodeName() {
        return getProperty(CHAIN_CODE_NAME);

    }
    /**
     * Returns chain ID
     *
     * @return
     */
    public String getChainId() {
        return getProperty(CHAIN_ID);

    }
    /**
     * Returns chaincode path
     *
     * @return
     */
    public String getChainCodePath() {
        return getProperty(CHAIN_CODE_PATH);

    }


    /**
     * Returns orderer address
     *
     * @return
     */
    public String getSoloOrdererAddress() {
        return getProperty(SOLO_ORDERER_ADDRESS);

    }

    /**
     * Returns peer address
     *
     * @return
     */
    public String getPeerAddress() {
        return getProperty(PEER_ADDRESS);

    }

    /**
     * Returns eventhub address
     *
     * @return
     */
    public String getEventAddress() {
        return getProperty(EVENT_ADDRESS);
    }

    /**
     * Returns ca service address
     *
     * @return
     */
    public String getCaAddress() {
        return getProperty(CA_ADDRESS);
    }

    public int getDeployWaittime() {
        return Integer.parseInt(getProperty(DEPLOY_WAITTIME));
    }

    public int getInvokeWaittime() {
        return Integer.parseInt(getProperty(INVOKE_WAITTIME));
    }
    public String getUserName_1() {
        return getProperty(USER_NAME_1);
    }

    public String getUserPswd_1() {
        return getProperty(USER_PSWD_1);
    }
    public String getUserName_2() {
        return getProperty(USER_NAME_2);
    }

    public String getUserPswd_2() {
        return getProperty(USER_PSWD_2);
    }
    public String getKeystoreProperties() {
        return getProperty(KEYSTORE_PROPERTIES);
    }

    public String getDeployFunction() {
        return getProperty(DEPLOY_FUNCTION);
    }

    public String[] getDeployArgs() {
        return getProperties(DEPLOY_ARGS);
    }

    public String getInvokeFunction() {
        return getProperty(INVOKE_FUNCTION);
    }

    public String[] getInvokeArgs() {
        return getProperties(INVOKE_ARGS);
    }

    public String[] getQueryArgs() {
        return getProperties(QUERY_ARGS);
    }
}
