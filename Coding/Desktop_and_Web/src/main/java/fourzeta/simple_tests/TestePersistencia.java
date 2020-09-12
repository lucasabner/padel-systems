package fourzeta.simple_tests;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import fourzeta.models.Circuito;
import fourzeta.resources.CircuitoResource;

public class TestePersistencia {
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		Circuito c = new Circuito();
		c.setNome("teste dois");
		c.setDescricao("Teste");
		CircuitoResource cr = new CircuitoResource();
		cr.registraCircuito(c);
	}
}