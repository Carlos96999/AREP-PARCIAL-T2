package edu.escuelaing.arep;
import org.json.JSONObject;
import static spark.Spark.port;
import static spark.Spark.get;
import spark.Response;
import spark.Request;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Calculadora calculadora = new Calculadora();
	
    public static void main( String[] args )
    {
        port(getPort());
        get("/ln", (req, res) -> inputDataPage(req, res, "ln"));
    }
    
    private static JSONObject inputDataPage(Request req, Response res, String operation)
    {
    	double numero = Double.parseDouble(req.queryParams("value"));
        JSONObject json = new JSONObject();
        json.put("operation",operation);
        json.put("input",numero);
        json.put("output",(operation.equals("ln"))? calculadora.logaritmoNatural(numero): calculadora.arcoTangente(numero));
        return json;
    }
    
    private static int getPort()
    {
    	int puerto = 0;
    	if(System.getenv("PORT") != null) puerto = Integer.parseInt(System.getenv("PORT"));
    	else puerto = 5000;
    	
    	return puerto;
    }
}
