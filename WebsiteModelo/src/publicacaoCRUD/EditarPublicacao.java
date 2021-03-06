package publicacaoCRUD;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PublicacaoDAO;
import models.Publicacao;

@WebServlet("/EditarPublicacao")
public class EditarPublicacao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarPublicacao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idPub"));
		String titulo = request.getParameter("titulo");
		String autores = request.getParameter("autores");
		String locPublicacao = request.getParameter("locPublicacao");
		String resumo = request.getParameter("resumo");
		String link = request.getParameter("link");
		
		Publicacao p = new Publicacao(titulo, autores, locPublicacao, resumo, link);
		
		PublicacaoDAO dao = new PublicacaoDAO();
		try {
			dao.alterarPublicacao(id, p);
		} catch (SQLException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erro.jsp");
			dispatcher.forward(request,response);
			e.printStackTrace();
			return;
		}
		
		request.setAttribute("msgSucesso", "Publicação alterada com sucesso!");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin");
		dispatcher.forward(request,response);
	}

}
