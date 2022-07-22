import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    
    public static void main(String[] args) throws Exception {
        // fazer a conexão HTTP e buscar os top 250 filmes
        String url = "https://api.nasa.gov/planetary/apod?api_key=oxiL3pB1vTzZ54Rj0vNCpcwcPK2b7BHmFJTolkHi";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        // exibir e manipular os dados
        ExtratorDeConteudoDaNasa extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();
        for (int i = 0; i < 2; i++) {
            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();            
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            
            geradora.cria(inputStream, nomeArquivo);
            
            
            System.out.println(conteudo.getTitulo());
            System.out.println();

        }
    }
}
