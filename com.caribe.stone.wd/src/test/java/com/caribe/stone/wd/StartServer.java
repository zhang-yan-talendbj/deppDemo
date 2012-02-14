package com.caribe.stone.wd;

import org.mortbay.jetty.Server;

public class StartServer {

	public static void main(String[] args) throws Exception {
		Server server = JettyUtils.buildNormalServer(8080, "/wd");
		server.start();
	}
}
