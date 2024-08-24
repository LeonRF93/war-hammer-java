package pantallas;


import kronk.lol.wh.PrincipalWh;

public class ScreenManager {
	
    private PrincipalWh principal;
	
	public ScreenManager(PrincipalWh principal) {
		this.principal = principal;
	}
	
	public void setPartida() {
		principal.setScreen(new Partida(principal));
	}

}
