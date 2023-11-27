package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import control.RequestPOST;
import model.Componente;

@WebServlet("/cadComponente")
public class ServletCadComp extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ServletCadComp() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Componente c = new Componente();
			c.setNome(request.getParameter("nome"));int vl = 0;

			if(request.getParameter("tipo") != null)
			{ vl = Integer.valueOf(request.getParameter("tipo"));

			}
			else 
			{
				vl = 0;
			}
			c.setTipo(vl);

			if(request.getParameter("tensao") != null)
			{ 
				vl = Integer.valueOf(request.getParameter("tensao"));

			}
			else 
			{
				vl = 0;
			}
			c.setTensao (vl);


			if(request.getParameter("gaveta") != null)
			{ 
				vl = Integer.valueOf(request.getParameter("gaveta"));
			}
			else 
			{
				vl = 0;
			}
			c.setGaveta(vl);

			if(request.getParameter("espaco") != null)
			{ 
				vl = Integer.valueOf(request.getParameter("espaco"));
			}
			else 
			{
				vl = 0;
			}
			c.setEspaco(vl);

			//Endpoint do cadastro

			String url = 
					"http://marcosvir.phost0001.servidorwebfacil.com/pee/cadcomp3.php";
			//instanciar o request POST
			RequestPOST rp = new RequestPOST(url);
			//Chamar método que realiza o request
			JSONObject jsonResp = rp.postJsonObject(c.toJsonObject());
			// resposta para o usuario
			System.out.print(jsonResp.toString());

		}	catch(Exception e) {
			System.out.print(e.getMessage());

		}


	}

}
