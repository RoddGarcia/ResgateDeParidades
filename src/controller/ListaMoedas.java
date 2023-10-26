package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.io.IOException;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class ListaMoedas {
	int mesInicial_;
	int mesFinal_;
	int mesUnico_;
	public Map<String, ArrayList<String>> dataMap = new HashMap<>();
	public Map<String, ArrayList<String>> cotacaoCompraMap = new HashMap<>();
    public Map<String, ArrayList<String>> cotacaoVendaMap = new HashMap<>();
    
	String dataFormatada;
	String cotacaoCompraStr;
    String cotacaoVendaStr;
	
    // ESTA FUNÇÃO DEVE SER TRABALHADA
	public void salvarLista(Object mesInicial, Object mesFinal, List<String> objetos) {
		this.mesInicial_ = (Integer) mesInicial;
		this.mesFinal_ = (Integer) mesFinal;
		
		System.out.println("Mês inicial:	" + mesInicial_ + "\nMês final:	" + mesFinal_);
		
		int maxDias = 31;
        int mes = mesFinal_;
        String ano = "2023";

        // Instância do HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Ir de moeda em moeda
        for (String tipo_moeda: objetos) {
        	ArrayList<String> dataLista = new ArrayList<>();
            ArrayList<String> cotacaoCompraLista = new ArrayList<>();
            ArrayList<String> cotacaoVendaLista = new ArrayList<>();
            
//        	System.out.println("Moeda atual:	"+tipo_moeda);
        	
	        for (int mesIdent = mesInicial_; mesIdent <= mes; mesIdent++) {
	        	
	        	for (int diaIdent = 1; diaIdent <= maxDias; diaIdent++) {
	        		
	        		if (tipo_moeda.equals("USD")) {
	        			String apiUrl = "";
	        			apiUrl = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?@moeda='"+tipo_moeda+"'&@dataCotacao='"+mesIdent+"-"+diaIdent+"-"+ano+"'&$top=100&$skip=0&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao";
	        		
	        			String cotacaoCompraStr = "";
	        			String cotacaoVendaStr = "";
	            
	        			// Cria um HttpRequest com a URL e o método GET
	        			HttpRequest request = HttpRequest.newBuilder()
	            			.uri(URI.create(apiUrl))
	            			.GET()
	            			.build();
	
	        			try {
	        				// Envia a requisição HTTP e recebe a resposta
	        				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
	        				// Verifica se a resposta foi bem-sucedida (status 200 OK)
	        				if (response.statusCode() == 200) {
	        					// Obtém o corpo da resposta como uma string
	        					String responseBody = response.body();

	        					JSONObject jsonResponse = new JSONObject(responseBody);
	        					JSONArray values = jsonResponse.getJSONArray("value");

	        					if (values.length() > 0) {
	        						String dataFormatada = diaIdent+"/"+mesIdent+"/"+ano;

	        						// Get the last entry in the array
	        						JSONObject lastEntry = values.getJSONObject(values.length() - 1);

	        						// Get the values of "cotacaoCompra" and "cotacaoVenda" from the last entry
	        						double cotacaoCompra = lastEntry.getDouble("cotacaoCompra");
	        						double cotacaoVenda = lastEntry.getDouble("cotacaoVenda");

	        						cotacaoCompraStr = String.format("%.4f", cotacaoCompra);
	        						cotacaoVendaStr = String.format("%.4f", cotacaoVenda);

	        						dataLista.add(dataFormatada);
	        						cotacaoCompraLista.add(cotacaoCompraStr);
	        						cotacaoVendaLista.add(cotacaoVendaStr);
	        					}
	        				}
	        			} catch (IOException | InterruptedException e) { 
	        				System.out.println("error ------------");
	        				e.printStackTrace();
	        			}
	        			dataMap.put(tipo_moeda, dataLista);
	        			cotacaoCompraMap.put(tipo_moeda, cotacaoCompraLista);
	        			cotacaoVendaMap.put(tipo_moeda, cotacaoVendaLista);
	        			
	        		} else {
	        			
	        			String apiUrl = "";
	        			apiUrl = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?@moeda='"+tipo_moeda+"'&@dataCotacao='"+mesIdent+"-"+diaIdent+"-"+ano+"'&$top=100&$format=json&$select=paridadeCompra,paridadeVenda,dataHoraCotacao";
	        			
	        			String paridadeCompraStr = "";
	        			String paridadeVendaStr = "";
	            
	        			// Cria um HttpRequest com a URL e o método GET
	        			HttpRequest request = HttpRequest.newBuilder()
	            			.uri(URI.create(apiUrl))
	            			.GET()
	            			.build();
	
	        			try {
	        				// Envia a requisição HTTP e recebe a resposta
	        				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
	        				// Verifica se a resposta foi bem-sucedida (status 200 OK)
	        				if (response.statusCode() == 200) {
	        					// Obtém o corpo da resposta como uma string
	        					String responseBody = response.body();

	        					JSONObject jsonResponse = new JSONObject(responseBody);
	        					JSONArray values = jsonResponse.getJSONArray("value");

	        					if (values.length() > 0) {
	        						String dataFormatada = diaIdent+"/"+mesIdent+"/"+ano;

	        						// Get the last entry in the array
	        						JSONObject lastEntry = values.getJSONObject(values.length() - 1);

	        						// Get the values of "cotacaoCompra" and "cotacaoVenda" from the last entry
	        						double paridadeCompra = lastEntry.getDouble("paridadeCompra");
	        						double paridadeVenda = lastEntry.getDouble("paridadeVenda");

	        						paridadeCompraStr = String.format("%.4f", paridadeCompra);
	        						paridadeVendaStr = String.format("%.4f", paridadeVenda);

	        						dataLista.add(dataFormatada);
	        						cotacaoCompraLista.add(paridadeCompraStr);
	        						cotacaoVendaLista.add(paridadeVendaStr);
	        					}
	        				}
	        			} catch (IOException | InterruptedException e) { 
	        				System.out.println("error ------------");
	        				e.printStackTrace();
	        			}
	        			dataMap.put(tipo_moeda, dataLista);
	        			cotacaoCompraMap.put(tipo_moeda, cotacaoCompraLista);
	        			cotacaoVendaMap.put(tipo_moeda, cotacaoVendaLista);
	        		}
	        	}
	        	
	        }
        }
	}

	public void salvarLista(Object numMes, List<String> tipos_moedas) {
		
		this.mesUnico_ = (Integer) numMes;
		
		int maxDias = 31;
        String ano = "2023";

        // Instância do HttpClient
        HttpClient httpClient = HttpClient.newHttpClient();

        // Ir de moeda em moeda
        for (String tipo_moeda: tipos_moedas) {
        	ArrayList<String> dataLista = new ArrayList<>();
            ArrayList<String> cotacaoCompraLista = new ArrayList<>();
            ArrayList<String> cotacaoVendaLista = new ArrayList<>();
        	
	        	for (int diaIdent = 1; diaIdent <= maxDias; diaIdent++) {
	        		if (tipo_moeda.equals("USD")) {
	        			String apiUrl = "";
	        			apiUrl = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?@moeda='"+tipo_moeda+"'&@dataCotacao='"+numMes+"-"+diaIdent+"-"+ano+"'&$top=100&$skip=0&$format=json&$select=cotacaoCompra,cotacaoVenda,dataHoraCotacao";
	        		
	        			String cotacaoCompraStr = "";
	        			String cotacaoVendaStr = "";
	            
	        			// Cria um HttpRequest com a URL e o método GET
	        			HttpRequest request = HttpRequest.newBuilder()
	            			.uri(URI.create(apiUrl))
	            			.GET()
	            			.build();
	
	        			try {
	        				// Envia a requisição HTTP e recebe a resposta
	        				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
	        				// Verifica se a resposta foi bem-sucedida (status 200 OK)
	        				if (response.statusCode() == 200) {
	        					// Obtém o corpo da resposta como uma string
	        					String responseBody = response.body();

	        					JSONObject jsonResponse = new JSONObject(responseBody);
	        					JSONArray values = jsonResponse.getJSONArray("value");

	        					if (values.length() > 0) {
	        						String dataFormatada = diaIdent+"/"+numMes+"/"+ano;

	        						// Get the last entry in the array
	        						JSONObject lastEntry = values.getJSONObject(values.length() - 1);

	        						// Get the values of "cotacaoCompra" and "cotacaoVenda" from the last entry
	        						double cotacaoCompra = lastEntry.getDouble("cotacaoCompra");
	        						double cotacaoVenda = lastEntry.getDouble("cotacaoVenda");

	        						cotacaoCompraStr = String.format("%.4f", cotacaoCompra);
	        						cotacaoVendaStr = String.format("%.4f", cotacaoVenda);

	        						dataLista.add(dataFormatada);
	        						cotacaoCompraLista.add(cotacaoCompraStr);
	        						cotacaoVendaLista.add(cotacaoVendaStr);
	        					}
	        				}
	        			} catch (IOException | InterruptedException e) { 
	        				System.out.println("error ------------");
	        				e.printStackTrace();
	        			}
	        			dataMap.put(tipo_moeda, dataLista);
	        			cotacaoCompraMap.put(tipo_moeda, cotacaoCompraLista);
	        			cotacaoVendaMap.put(tipo_moeda, cotacaoVendaLista);
	        			
	        		} else {
	        			
	        			String apiUrl = "";
	        			apiUrl = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoMoedaDia(moeda=@moeda,dataCotacao=@dataCotacao)?@moeda='"+tipo_moeda+"'&@dataCotacao='"+numMes+"-"+diaIdent+"-"+ano+"'&$top=100&$format=json&$select=paridadeCompra,paridadeVenda,dataHoraCotacao";
	        			
	        			String paridadeCompraStr = "";
	        			String paridadeVendaStr = "";
	            
	        			// Cria um HttpRequest com a URL e o método GET
	        			HttpRequest request = HttpRequest.newBuilder()
	            			.uri(URI.create(apiUrl))
	            			.GET()
	            			.build();
	
	        			try {
	        				// Envia a requisição HTTP e recebe a resposta
	        				HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		
	        				// Verifica se a resposta foi bem-sucedida (status 200 OK)
	        				if (response.statusCode() == 200) {
	        					// Obtém o corpo da resposta como uma string
	        					String responseBody = response.body();

	        					JSONObject jsonResponse = new JSONObject(responseBody);
	        					JSONArray values = jsonResponse.getJSONArray("value");

	        					if (values.length() > 0) {
	        						String dataFormatada = diaIdent+"/"+numMes+"/"+ano;

	        						// Get the last entry in the array
	        						JSONObject lastEntry = values.getJSONObject(values.length() - 1);

	        						// Get the values of "cotacaoCompra" and "cotacaoVenda" from the last entry
	        						double paridadeCompra = lastEntry.getDouble("paridadeCompra");
	        						double paridadeVenda = lastEntry.getDouble("paridadeVenda");

	        						paridadeCompraStr = String.format("%.4f", paridadeCompra);
	        						paridadeVendaStr = String.format("%.4f", paridadeVenda);

	        						dataLista.add(dataFormatada);
	        						cotacaoCompraLista.add(paridadeCompraStr);
	        						cotacaoVendaLista.add(paridadeVendaStr);
	        					}
	        				}
	        			} catch (IOException | InterruptedException e) { 
	        				System.out.println("error ------------");
	        				e.printStackTrace();
	        			}
	        			dataMap.put(tipo_moeda, dataLista);
	        			cotacaoCompraMap.put(tipo_moeda, cotacaoCompraLista);
	        			cotacaoVendaMap.put(tipo_moeda, cotacaoVendaLista);
	        		}
	        	}
	        	
	        dataMap.put(tipo_moeda, dataLista);
	        cotacaoCompraMap.put(tipo_moeda, cotacaoCompraLista);
	        cotacaoVendaMap.put(tipo_moeda, cotacaoVendaLista);
        }
	}

	public ArrayList<String> getListaMoedas () {
		return new ArrayList<>(dataMap.keySet());
	}
	
    public ArrayList<String> getDataLista(String currency) {
        return dataMap.get(currency);
    }

    public ArrayList<String> getCotacaoCompraLista(String currency) {
        return cotacaoCompraMap.get(currency);
    }

    public ArrayList<String> getCotacaoVendaLista(String currency) {
        return cotacaoVendaMap.get(currency);
    }
    
    public Map<String, ArrayList<String>> getDataLista() {
        return dataMap;
    }

    public Map<String, ArrayList<String>> getCotacaoCompraLista() {
        return cotacaoCompraMap;
    }

    public Map<String, ArrayList<String>> getCotacaoVendaLista() {
        return cotacaoVendaMap;
    }
}