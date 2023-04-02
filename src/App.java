import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer conexão HTTP e buscar os top 250 series
        //String url = "https://imdb-api.com/en/API/Top250Movies/k_60qd4492";
        String  url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";

        var http = new ClientHttp();
        String json = http.buscaDados(url);

        // extrair dados principais da API (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaConteudo = parser.parse(json);


        //se não  existir um diretorio para as figurinhas, ele irá criar
        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        // exibir e manipular os dados
        var geradorFigurinha = new GeradorDeFigurinhas();

        for(int i =0;i<2;i++){
            Map<String, String> conteudo = listaConteudo.get(i);

            String urlImagem = conteudo.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            String titulo = conteudo.get("title");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = "figurinhas/"+ titulo + ".png";

            geradorFigurinha.criar(inputStream, nomeArquivo);

            System.out.println("Título:" + "\u001b[1m" + conteudo.get("title") + "\u001b[m");
            //System.out.println(conteudo.get("image"));
            System.out.println(conteudo.get("imDbRating"));
            double nota = Double.parseDouble(conteudo.get("imDbRating"));
            int numEstrelas = (int) nota;

            for (int n = 1; n <= numEstrelas; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }

    }
}
