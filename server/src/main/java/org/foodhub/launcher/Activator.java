package org.foodhub.launcher;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.foodhub.common.json.JsonFactory;
import org.foodhub.restaurant.controller.RestaurantFoodController;
import org.foodhub.restaurant.controller.RestaurantProfileController;
import org.foodhub.user.controller.CartController;
import org.foodhub.user.controller.OrderController;
import org.foodhub.user.controller.UserController;

/**
 * <p>
 * Customizes the starting and stopping of a bundle and creates the JAX-RS server instance.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Activator implements BundleActivator {

    private static final Logger LOGGER = LogManager.getLogger(Activator.class);
    private Server server;

    /**
     * <p>
     * Invoked when the bundle is started and creates the server instance.
     * </p>
     *
     * @param context The execution context of the bundle being started.
     */
    @Override
    public void start(final BundleContext context) {
        final JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();

        bean.setAddress("/swiggy");
        bean.setProvider(JsonFactory.getInstance().getJsonProvider());
        bean.setServiceBeans(getServiceBeans());
        server = bean.create();

        LOGGER.info("Server Bundle Is Started");
    }

    /**
     * <p>
     * Gets all the service beans.
     * </p>
     *
     * @return The List of instances
     */
    private List<Object> getServiceBeans() {
        final List<Object> beans = new ArrayList<>();

        beans.add(RestaurantProfileController.getInstance());
        beans.add(RestaurantFoodController.getInstance());
        beans.add(UserController.getInstance());
        beans.add(CartController.getInstance());
        beans.add(OrderController.getInstance());

        return beans;
    }

    /**
     * <p>
     * Invoked when the bundle is stopped and destroys the sever instance.
     * </p>
     *
     * @param context The execution context of the bundle being stopped.
     */
    @Override
    public void stop(final BundleContext context) {
        if (null != server) {
            server.destroy();
        }
        LOGGER.info("Server Bundle Is Stopped");
    }
}
