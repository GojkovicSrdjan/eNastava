package rs.ac.uns.ftn.tseo.ssd;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class TseoSSDApplication {

	public static void main( String[] args )
    {
		System.out.println("   ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~  ");
    	SpringApplication.run(TseoSSDApplication.class, args);
    	System.out.println("   ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~ ~♫~  ");
    	
    }
	
	/**
	 * Submitting a Multipart request (multipart/form-data) using PUT and Spring-boot
	 */
	@Bean
	public MultipartResolver multipartResolver() {
	   return new StandardServletMultipartResolver() {
	     @Override
	     public boolean isMultipart(HttpServletRequest request) {
	        String method = request.getMethod().toLowerCase();
	        //By default, only POST is allowed. Since this is an 'update' we should accept PUT.
	        if (!Arrays.asList("put", "post").contains(method)) {
	           return false;
	        }
	        String contentType = request.getContentType();
	        return (contentType != null &&contentType.toLowerCase().startsWith("multipart/"));
	     }
	   };
	}
}
