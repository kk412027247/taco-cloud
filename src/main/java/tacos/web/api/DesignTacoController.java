package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.List;
import java.util.Optional;


@RestController("DesignTacoControllerApi")
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins="*")
public class DesignTacoController {
    private final TacoRepository tacoRepo;

//    @Autowired
//    EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepo) {
        this.tacoRepo = tacoRepo;
    }

    @GetMapping("/recent")
    public Iterable<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12, Sort.by("createdAt").descending());
        return tacoRepo.findAll(page).getContent();
    }

//    public Resource<TacoResource> recentTacosH(){
//        PageRequest page = PageRequest.of(0, 12, Sort.by("createAt").descending());
//        List<Taco> tacos = tacoRepo.findAll(page).getContent();
//        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);
//        Resources <TacoResource> recentResources = new Resources<TacoResource>(tacoResources);
//        recentResources.add(linkTo(methodOn(DesignTacoController.class).recentTacos()).withRel("recents"));
//        return recentResources;
//    }

    @GetMapping("/{id}")
    public Taco tacoById(@PathVariable("id") Long id){
        Optional<Taco> optTaco = tacoRepo.findById(id);
        return optTaco.orElse(null);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){
        return tacoRepo.save(taco);
    }
}
