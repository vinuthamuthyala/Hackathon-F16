package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@WebServlet("/register")
public class Register extends HttpServlet{

	public Register()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			MongoClientURI uri = new MongoClientURI("mongodb://vinutha:muthyala@ds151137.mlab.com:51137/communityforum");
			MongoClient client = new MongoClient(uri);
			DB db = client.getDB(uri.getDatabase());
			DBCollection users = db.getCollection("UserRecords");
			BasicDBObject query = new BasicDBObject();
			String firstname=request.getParameter("FirstName");
			String lastname=request.getParameter("LastName");
			String email=request.getParameter("email");
			String password=request.getParameter("EnterPassword");
			String confpasswd=request.getParameter("ConfirmPassword");
			query.put("First Name",firstname);
			query.put("Last Name",lastname);
			query.put("Email",email);
			System.out.println(email);
			if(password==confpasswd){
				query.put("Password",password);
			}
			else
			{
				
			}
			DBCursor docs = users.find(query);
			response.getWriter().write(docs.toArray().toString());
			
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "GET");
			response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			response.setHeader("Access-Control-Max-Age", "86400");
			System.out.println("Insert doget");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
