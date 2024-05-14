package ch.zhaw.iwi.devops.demo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ContactController {

    private Map<Integer, Contact> contacts = new HashMap<Integer, Contact>();

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        this.contacts.put(1,new Contact(1, "Aghishenth", "Thayalakumar"));
        this.contacts.put(2,new Contact(2, "Ueli", "Mueller"));
        this.contacts.put(3,new Contact(3, "Herbert", "Hansli"));
        this.contacts.put(4,new Contact(4, "Hans", "Peter"));
        System.out.println("Init Data");
    }

    @GetMapping("/test-contact")
    public String test() {
        return "Contact app is up and running!";
    }

    @GetMapping("/services/ping-contact")
    public String ping() {
        String languageCode = "de";
        return "{ \"status\": \"ok\", \"userId\": \"admin"+ "\", \"languageCode\": \"" + languageCode + "\",\"version\": \"0.0.1" + "\"}";
    }

    @GetMapping("/count-contact")
    public int count() {
        return this.contacts.size();
    }

    @GetMapping("/services/contact")
    public List<PathListEntry<Integer>> contact() {
        var result = new ArrayList<PathListEntry<Integer>>();
        for (var contact : this.contacts.values()) {
            var entry = new PathListEntry<Integer>();
            entry.setKey(contact.getId(), "contactKey");
            entry.setName(contact.getVorname());
            entry.getDetails().add(contact.getNachname());
            entry.setTooltip(contact.getNachname());
            result.add(entry);
        }
        return result;
    }

    @GetMapping("/services/contact/{key}")
    public Contact getContact(@PathVariable Integer key) {
        return this.contacts.get(key);
    }

    @PostMapping("/services/contact")
    public void createContact(@RequestBody Contact contact) {
        var newId = this.contacts.keySet().stream().max(Comparator.naturalOrder()).orElse(0) + 1;
        contact.setId(newId);
        this.contacts.put(newId, contact);
    }

    @PutMapping("/services/contact/{id}")
    public void updateContact(@PathVariable Integer id, @RequestBody Contact contact) {
        contact.setId(id);
        this.contacts.put(id, contact);
    }

    @DeleteMapping("/services/contact/{key}")
    public Contact deleteContact(@PathVariable Integer key) {
        return this.contacts.remove(key);
    }
}
