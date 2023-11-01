package sep3datalayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import sep3datalayer.models.CenterEntity;
import sep3datalayer.repos.CenterRepository;
import sep3datalayer.services.CenterServiceImpl;
import sep3datalayer.services.interfaces.CenterService;

@SpringBootApplication public class Test
{
  public static void main(String[] args)
  {
    ConfigurableApplicationContext context = SpringApplication.run(Test.class);
    CenterRepository centerRepository = context.getBean(CenterRepository.class);
    CenterEntity center  = new CenterEntity("TestNummer2", "Horsens");

    CenterService centerService = new CenterServiceImpl(centerRepository);
    centerService.addCenter(center);

    context.close();
  }
}
