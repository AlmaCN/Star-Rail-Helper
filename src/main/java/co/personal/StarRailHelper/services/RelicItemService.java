package co.personal.StarRailHelper.services;

import co.personal.StarRailHelper.entites.DTO.RelicItemDTO;
import co.personal.StarRailHelper.entites.RelicItem;
import co.personal.StarRailHelper.entites.Relics;
import co.personal.StarRailHelper.exceptions.EnhancementItemsNotFoundException;
import co.personal.StarRailHelper.exceptions.RelicsNotFoundException;
import co.personal.StarRailHelper.repositories.RelicItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelicItemService {

    @Autowired
    private RelicsService relicsService;

    @Autowired
    private RelicItemRepository relicItemRepository;

    private RelicItem getEnhancementItemById(Long id) throws EnhancementItemsNotFoundException{
        Optional<RelicItem> optionalEnhancementItems = relicItemRepository.findById(id);
        if(optionalEnhancementItems.isPresent()){
            return optionalEnhancementItems.get();
        } else {
            throw new EnhancementItemsNotFoundException("Enhancement item with id " + id + " not found");
        }
    }

    public RelicItem save(RelicItemDTO relicItemDTO) throws RelicsNotFoundException{

        Relics relics = relicsService.readByName(relicItemDTO.getRelicName());

        RelicItem relicItem = new RelicItem(
                relicItemDTO.getName(),
                relicItemDTO.getCollected(),
                relicItemDTO.getNeeded(),
                relics);
        return relicItemRepository.save(relicItem);
    }

    public RelicItem read(Long id) throws EnhancementItemsNotFoundException {
        return getEnhancementItemById(id);
    }

    public RelicItem readByName(String name) throws EnhancementItemsNotFoundException {
        Optional<RelicItem> optionalEnhancementItems = relicItemRepository.findByName(name);
        if(optionalEnhancementItems.isPresent()){
            return optionalEnhancementItems.get();
        } else {
            throw new EnhancementItemsNotFoundException("Enhancement item with name " + name + " not found");
        }
    }

    public RelicItem update(Long id, RelicItem relicItem) throws EnhancementItemsNotFoundException {
        RelicItem relicItemUpdate = getEnhancementItemById(id);
        relicItemUpdate.setName(relicItem.getName());
        relicItemUpdate.setCollected(relicItem.getCollected());
        relicItemUpdate.setNeeded(relicItem.getNeeded());
        relicItemUpdate.setRelics(relicItem.getRelics());
        return relicItemRepository.save(relicItemUpdate);
    }

    public void delete(Long id) throws EnhancementItemsNotFoundException{
        RelicItem relicItem = getEnhancementItemById(id);
        relicItemRepository.delete(relicItem);
    }
}
