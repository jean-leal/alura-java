import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // fazer a conexão HTTP e buscar os top 250 filmes
        String url = "https://api.mocki.io/v2/549a5d8b/Top250Movies";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // pegar somente os dados que nos interessam (titúlo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 10; i++) {
            Map<String,String> filme = listaDeFilmes.get(i);

            String urlImagem = filme.get("image");

            String titulo = filme.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();            
            String nomeArquivo ="../saida/" + titulo + ".png";
            
            geradora.cria(inputStream, nomeArquivo);
            

            System.out.println(titulo);
            System.out.println();

        }
    }
}
