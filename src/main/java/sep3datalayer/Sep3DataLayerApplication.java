package sep3datalayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.repos.CenterRepository;

@SpringBootApplication public class Sep3DataLayerApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(Sep3DataLayerApplication.class, args);
  }
}
