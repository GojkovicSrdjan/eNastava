package rs.ac.uns.ftn.tseo.ssd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class TseoSSDAplication {

	public static void main( String[] args )
    {
    	SpringApplication.run(TseoSSDAplication.class, args);
    }
	
}
