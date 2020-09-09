package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import connection.IElement;
import model.Atleta;
import model.Chave;
import model.Dupla;
import model.Jogo;
import model.Torneio;

public class GeraPDF {

	static BaseColor AMARELO = new BaseColor(255, 255, 0);
	static BaseColor CINZA_CLARO = new BaseColor(233, 233, 233);
	static BaseColor CINZA_ESCURO = new BaseColor(211, 211, 211);

	// ok
	private static PdfPTable buildCabecalho() {
		try {
			Image imgLogo = Image.getInstance("assets\\FourZeta.png");
			imgLogo.setAlignment(0);
			imgLogo.scaleToFit(100, 100); // Scale por Pixel

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

			PdfPTable cabecalho = new PdfPTable(3); // 3 -> Numero de Colunas
			PdfPCell cellcab1 = new PdfPCell(imgLogo); // Criando uma celula
			PdfPCell cellcab2 = new PdfPCell(new Paragraph("FOURZETA"));
			PdfPCell cellcab3 = new PdfPCell(new Paragraph("Data/Hora: " + sdf.format(new Date())));
			cellcab1.setBorder(0);
			cellcab2.setBorder(0);
			cellcab2.setPaddingTop(45);
			cellcab2.setHorizontalAlignment(1);
			cellcab3.setBorder(0);
			cellcab3.setHorizontalAlignment(2);
			cabecalho.addCell(cellcab1);
			cabecalho.addCell(cellcab2);
			cabecalho.addCell(cellcab3);

			return cabecalho;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (BadElementException e) {
			e.printStackTrace();
		}
		return null;
	}

	// ok
	public void listaInscritosPDF(String titulo, String caminho, List<Dupla> iterable) {
		// Criando um documento vazio
		Document documentoPDF = new Document();

		try {
			// Cria uma instancia do documento e da o nome do pdf
			PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminho + "\\" + titulo + ".pdf"));

			// Abrir o documento
			documentoPDF.open();

			// Setando o tamnho da pagina
			documentoPDF.setPageSize(PageSize.A4);

			// <-- INICIO CABECALHO -->
			PdfPTable cabecalho = buildCabecalho();
			documentoPDF.add(cabecalho);
			// <-- FIM CABECALHO /-->

			// Adicionar paragrafo
			Paragraph p = new Paragraph(titulo);
			p.setAlignment(1);
			p.setSpacingBefore(10);
			documentoPDF.add(p);

			p = new Paragraph(" "); // Para pular uma linha
			documentoPDF.add(p);

			// <-- INICIO TABELA -->
			PdfPTable table = new PdfPTable(3); // 3 -> Numero de Colunas
			// Cabecalho Tabela
			PdfPCell cell1 = new PdfPCell(new Paragraph("Dupla")); // Criando uma celula
			PdfPCell cell2 = new PdfPCell(new Paragraph("Pontuação")); // Criando uma celula
			PdfPCell cell3 = new PdfPCell(new Paragraph("Situação")); // Criando uma celula
			cell1.setHorizontalAlignment(1);
			cell1.setBackgroundColor(AMARELO);
			cell2.setHorizontalAlignment(1);
			cell2.setBackgroundColor(AMARELO);
			cell3.setHorizontalAlignment(1);
			cell3.setBackgroundColor(AMARELO);
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);

			// PreencheTabela
			int mod = iterable.size() % 3;
			int ctrl1 = 1;
			for (IElement element : iterable) {
				Dupla dupla = (Dupla) element;
				cell1 = new PdfPCell(new Paragraph(dupla.toString()));
				cell1.setHorizontalAlignment(1);
				String pontos = "" + dupla.getPonTotal();
				cell2 = new PdfPCell(new Paragraph(pontos));
				cell2.setHorizontalAlignment(1);
				if (mod == 0) {
					cell3 = new PdfPCell(new Paragraph("Inscritos"));
				} else {
					if ((iterable.indexOf(dupla) == (iterable.size() - 1))
							|| (iterable.indexOf(dupla) == (iterable.size() - 2))) {
						cell3 = new PdfPCell(new Paragraph("Suplentes"));
					} else {
						cell3 = new PdfPCell(new Paragraph("Inscritos"));
					}
				}

				cell3.setHorizontalAlignment(1);
				if (ctrl1 % 2 == 1) {
					cell1.setBackgroundColor(CINZA_CLARO);
					cell2.setBackgroundColor(CINZA_CLARO);
					cell3.setBackgroundColor(CINZA_CLARO);
					ctrl1++;
				} else {
					cell1.setBackgroundColor(CINZA_ESCURO);
					cell2.setBackgroundColor(CINZA_ESCURO);
					cell3.setBackgroundColor(CINZA_ESCURO);
					ctrl1++;
				}
				table.addCell(cell1);
				table.addCell(cell2);
				table.addCell(cell3);
			}
			documentoPDF.add(table);
			// <-- FIM TABELA /-->

			// Criando Nova Pagina
//			documentoPDF.newPage();
//			p = new Paragraph("TESTEEE");
//			documentoPDF.add(p);

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			documentoPDF.close();
		}
	}

	// ok
	public void listaChavesPDF(String titulo, String caminho, Iterable<Chave> iterable) {
		// Criando um documento vazio
		Document documentoPDF = new Document();

		try {

			// Cria uma instancia do documento e da o nome do pdf
			PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminho + "\\" + titulo + ".pdf"));

			// Abrir o documento
			documentoPDF.open();

			// Setando o tamnho da pagina
			documentoPDF.setPageSize(PageSize.A4);

			// <-- INICIO CABECALHO -->
			PdfPTable cabecalho = buildCabecalho();
			documentoPDF.add(cabecalho);
			// <-- FIM CABECALHO /-->

			// Adicionar paragrafo
			Paragraph p = new Paragraph("Lista de Chaves");
			p.setAlignment(1);
			p.setSpacingBefore(10);
			documentoPDF.add(p);

			p = new Paragraph(" "); // Para pular uma linha
			documentoPDF.add(p);

			// <-- INICIO TABELA -->
			PdfPTable table = new PdfPTable(1); // 3 -> Numero de Colunas
			// Cabecalho Tabela
			PdfPCell cell1; // Criando uma celula

			// PreencheTabela
			for (IElement element : iterable) {
                 Chave chave = (Chave) element;
				// NOME
				cell1 = new PdfPCell(new Paragraph(chave.getNome()));
				cell1.setBackgroundColor(AMARELO);
				cell1.setHorizontalAlignment(1);
				table.addCell(cell1);

				// DUPLA 1
				cell1 = new PdfPCell(new Paragraph(chave.getDupla1().getAtletas().get(0).getNome() + " & "
						+ chave.getDupla1().getAtletas().get(1).getNome()));
				cell1.setBackgroundColor(CINZA_CLARO);
				cell1.setHorizontalAlignment(1);
				table.addCell(cell1);

				// DUPLA 2
				cell1 = new PdfPCell(new Paragraph(chave.getDupla2().getAtletas().get(0).getNome() + " & "
						+ chave.getDupla2().getAtletas().get(1).getNome()));
				cell1.setBackgroundColor(CINZA_CLARO);
				cell1.setHorizontalAlignment(1);
				table.addCell(cell1);

				// DUPLA 3
				cell1 = new PdfPCell(new Paragraph(chave.getDupla3().getAtletas().get(0).getNome() + " & "
						+ chave.getDupla3().getAtletas().get(1).getNome()));
				cell1.setBackgroundColor(CINZA_CLARO);
				cell1.setHorizontalAlignment(1);
				table.addCell(cell1);
			}

			documentoPDF.add(table);
			// <-- FIM TABELA /-->

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			documentoPDF.close();
		}
	}

	// ok
	public void listaJogosPDF(String titulo, String caminho, List<Chave> chaves) {
		// Criando um documento vazio
		Document documentoPDF = new Document();

		try {

			// Cria uma instancia do documento e da o nome do pdf
			PdfWriter.getInstance(documentoPDF, new FileOutputStream(caminho + "\\" + titulo + ".pdf"));

			// Abrir o documento
			documentoPDF.open();

			// Setando o tamnho da pagina
			documentoPDF.setPageSize(PageSize.A4);

			// <-- INICIO CABECALHO -->
			PdfPTable cabecalho = buildCabecalho();
			documentoPDF.add(cabecalho);
			// <-- FIM CABECALHO /-->

			// Adicionar paragrafo
			Paragraph p = new Paragraph(titulo);
			p.setAlignment(1);
			p.setSpacingBefore(10);
			documentoPDF.add(p);

			p = new Paragraph(" "); // Para pular uma linha
			documentoPDF.add(p);

			// <-- INICIO TABELA -->
			PdfPTable table = new PdfPTable(1); // 3 -> Numero de Colunas
			// Cabecalho Tabela
			PdfPCell cell1; // Criando uma celula

			// PreencheTabela
			for (IElement element : chaves) {
				Chave chave = (Chave) element;

				// NOME
				cell1 = new PdfPCell(new Paragraph(chave.getNome()));
				cell1.setBackgroundColor(AMARELO);
				cell1.setHorizontalAlignment(1);
				table.addCell(cell1);

				int aux = chave.getJogos().size();
				for (int i = 0; i < aux; i++) {
					cell1 = new PdfPCell(new Paragraph(chave.getJogos().get(i).getPartida()));
					cell1.setBackgroundColor(CINZA_CLARO);
					cell1.setHorizontalAlignment(1);
					table.addCell(cell1);
				}
			}

			documentoPDF.add(table);
			// <-- FIM TABELA /-->

		} catch (DocumentException de) {
			de.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			documentoPDF.close();
		}
	}

	// main para testar a geracao dos pdfs
	public static void main(String[] args) {
		GeraPDF gerar = new GeraPDF();

		Atleta a1 = new Atleta();
		a1.setNome("Atleta 01");
		a1.setCpf("CPF 1");
		a1.setTel("Tel 1");

		Atleta a2 = new Atleta();
		a2.setNome("Atleta 02");
		a2.setCpf("CPF 2");
		a2.setTel("Tel 2");

		Atleta a3 = new Atleta();
		a3.setNome("Atleta 03");
		a3.setCpf("CPF 3");
		a3.setTel("Tel 3");

		Atleta a4 = new Atleta();
		a4.setNome("Atleta 04");
		a4.setCpf("CPF 4");
		a4.setTel("Tel 4");

		Atleta a5 = new Atleta();
		a5.setNome("Atleta 05");

		Atleta a6 = new Atleta();
		a6.setNome("Atleta 06");

		List<Atleta> atletas = new ArrayList<Atleta>();
		atletas.add(a1);
		atletas.add(a2);
		atletas.add(a3);
		atletas.add(a4);
		atletas.add(a5);
		atletas.add(a6);

		List<Atleta> ad1 = new ArrayList<Atleta>();
		ad1.add(a1);
		ad1.add(a2);

		List<Atleta> ad2 = new ArrayList<Atleta>();
		ad2.add(a3);
		ad2.add(a4);

		List<Atleta> ad3 = new ArrayList<Atleta>();
		ad3.add(a5);
		ad3.add(a6);

		Dupla d1 = new Dupla();
		d1.setAtletas(ad1);
		d1.setPonTotal(10);

		Dupla d2 = new Dupla();
		d2.setAtletas(ad2);
		d2.setPonTotal(10);

		Dupla d3 = new Dupla();
		d3.setAtletas(ad3);
		d3.setPonTotal(10);

		Dupla d4 = new Dupla();
		d4.setAtletas(ad2);
		d4.setPonTotal(10);

		Dupla d5 = new Dupla();
		d5.setAtletas(ad3);
		d5.setPonTotal(10);

		Dupla d6 = new Dupla();
		d6.setAtletas(ad3);
		d6.setPonTotal(10);

		List<Dupla> duplas = new ArrayList<Dupla>();
		duplas.add(d1);
		duplas.add(d2);
		duplas.add(d3);
		duplas.add(d4);
		duplas.add(d5);
		duplas.add(d6);

		List<Chave> chaves = new ArrayList<Chave>();
		Torneio t = new Torneio();
		chaves = t.montarChave(duplas);

		String cam = "C:\\\\Users\\\\Arthur\\\\Desktop";
		String tit;

		Jogo j1 = new Jogo();
		j1.setPartida("Partida 1");

		Jogo j2 = new Jogo();
		j2.setPartida("Partida 2");

		Jogo j3 = new Jogo();
		j3.setPartida("Partida 3");

		Jogo j4 = new Jogo();
		j4.setPartida("Partida 1");

		Jogo j5 = new Jogo();
		j5.setPartida("Partida 2");

		Jogo j6 = new Jogo();
		j6.setPartida("Partida 3");

		List<Jogo> jogos = new ArrayList<Jogo>();
		jogos.add(j1);
		jogos.add(j2);
		jogos.add(j3);
		List<Jogo> jogos2 = new ArrayList<Jogo>();
		jogos2.add(j4);
		jogos2.add(j5);
		jogos2.add(j6);
//
//		chaves.get(0).setJogos(jogos);
//		chaves.get(1).setJogos(jogos2);

		tit = "Lista de Jogos";
		gerar.listaJogosPDF(tit, cam, chaves);

		tit = "Lista de Chaves";
		gerar.listaChavesPDF(tit, cam, chaves);

		tit = "Lista de Inscricao";
		duplas.remove(duplas.size() - 1);
		gerar.listaInscritosPDF(tit, cam, duplas);

	}

}
