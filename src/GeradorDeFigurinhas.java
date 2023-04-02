
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.imageio.ImageIO;

public class GeradorDeFigurinhas {
    public void criar(InputStream inputStream, String nomeArquivo) throws IOException{
        //leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@.jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //criar nova imagem com transparencia e tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura +200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original pra nova imagem
        Graphics2D graphics =(Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0,0,null);

        //configurar fonte
        Font fonte = new Font(Font.SANS_SERIF,Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

        //escrever uma frase na imagem
        String texto = "BOM D+2";
        //pegando a altura e largura do texto
        FontMetrics fontMetrics = graphics.getFontMetrics();       
        Rectangle2D retangulo = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) retangulo.getWidth();
        int posicaoXtexto = (largura = larguraTexto)/2;
        graphics.drawString(texto, posicaoXtexto, novaAltura-100);

        //escrever a nova imagem num arquivo
        ImageIO.write(novaImagem, "png", new File(nomeArquivo));

    }

}

