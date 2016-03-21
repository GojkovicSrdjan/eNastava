package rs.ac.uns.ftn.tseo.ssd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // same as @Configuration @EnableAutoConfiguration @ComponentScan
public class TseoSSDApplication {

	public static void main( String[] args )
    {
		System.out.println("			✟	✟	✟			");
    	SpringApplication.run(TseoSSDApplication.class, args);
    	System.out.println("			✟	✟	✟			");
    	
    }
	
}
