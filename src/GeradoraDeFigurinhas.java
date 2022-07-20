import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    public void cria() throws Exception {
        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMTY3OTI5NDczN15BMl5BanBnXkFtZTcwNDA0NDY3Mw@@..jpg").openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);


        // cria nova imegem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
                
        // copiar imagem original para nova imgem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);
        

        // escrever frase na nova imagem
        graphics.drawString("TOPZERA", 100, novaAltura - 100);
        
        // escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));
    }

    public static void main(String[] args) throws Exception {
        var gereadora = new GeradoraDeFigurinhas();
        gereadora.cria();
    }

}
