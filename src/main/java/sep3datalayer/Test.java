package sep3datalayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.repos.CenterRepository;

@SpringBootApplication public class Test
{
  public static void main(String[] args)
  {
    ConfigurableApplicationContext context = SpringApplication.run(Test.class);
    CenterRepository centerRepository = context.getBean(CenterRepository.class);
    CenterEntity center  = new CenterEntity("Test", "Horsens");

    centerRepository.save(center);
    context.close();
  }
}
