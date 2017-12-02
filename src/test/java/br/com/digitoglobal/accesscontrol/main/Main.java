package br.com.digitoglobal.accesscontrol.main;

import br.com.digitoglobal.accesscontrol.service.UsuarioService;
import br.com.digitoglobal.accesscontrol.util.SpringUtils;

public class Main {

    public static void main(String[] args) {

        UsuarioService service = SpringUtils.getBean(UsuarioService.class);
        System.out.println(service);

    }

}
