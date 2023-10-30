package HerskabPadel.services;

import main.HerskabPadel.models.CenterEntity;
import main.HerskabPadel.repos.CenterRepository;
import main.HerskabPadel.services.interfaces.CenterService;
import org.springframework.stereotype.Service;

@Service public class CenterServiceImpl implements CenterService {

    private final CenterRepository centerRepository;


    @Override
    public void addCenter(CenterEntity centerEntity) {

    }
}
