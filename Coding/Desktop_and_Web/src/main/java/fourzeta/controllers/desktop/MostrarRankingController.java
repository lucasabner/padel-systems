package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import fourzeta.desktop_views.Inicial;
import fourzeta.desktop_views.RankingView;
import fourzeta.models.Usuario;

public class MostrarRankingController implements ActionListener {

	private Inicial tela;
	private Usuario usuario;
	private RankingView telaRanking;

	public MostrarRankingController(Usuario usuario, Inicial tela) {
		this.tela = tela;
		this.usuario = usuario;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			telaRanking = new RankingView(usuario);

			this.tela.setVisible(false);

			telaRanking.setVisible(true);

		} catch (ParseException | RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
