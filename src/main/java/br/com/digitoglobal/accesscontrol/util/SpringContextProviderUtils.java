package br.com.digitoglobal.accesscontrol.util;

import me.dabpessoa.framework.service.SpringContextProvider;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

public class SpringContextProviderUtils implements SpringContextProvider {

    @Override
    public ApplicationContext getContext(SpringContextLoadType springContextLoadType, String... strings) {
        return SpringUtils.getContext();
    }

    @Override
    public String[] getActiveProfiles() {
        return new String[0];
    }

}
