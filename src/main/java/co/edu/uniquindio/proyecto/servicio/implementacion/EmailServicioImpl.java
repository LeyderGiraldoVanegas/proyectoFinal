package co.edu.uniquindio.proyecto.servicio.implementacion;




import co.edu.uniquindio.proyecto.dto.EmailDTO;
import co.edu.uniquindio.proyecto.servicio.interfaces.EmailServicio;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class EmailServicioImpl implements EmailServicio {
    private final JavaMailSender javaMailSender;
    @Override
    public void enviarEmail(EmailDTO emailDTO) {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje);
        try{
            helper.setSubject(emailDTO.getAsunto());
            helper.setText(emailDTO.getCuerpo(), true);
            helper.setTo(emailDTO.getDestinatario());
            helper.setFrom("leyder.giraldov@uqvirtual.edu.co");//"no_reply@dominio.com");
            javaMailSender.send(mensaje);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
