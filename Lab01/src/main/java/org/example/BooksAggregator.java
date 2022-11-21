package org.example;

import org.example.model.Person;
import org.example.model.Samples;

import java.util.Random;

public class BooksAggregator {

    public void aggregateBooksThroughPeople(){

        for (Person person: Samples.getSampleListOfPeople())
        {
            int index = getRandomIndex();

            person.getBooks().add(Samples.getAvailableBooks().get(index));
            Samples.getAvailableBooks().get(index).setOwner(person);
            Samples.removeBook(Samples.getAvailableBooks().get(index));

            if(Samples.getAvailableBooks().size() == 0){
                break;
            }
        }

    }
    private int getRandomIndex(){
        return new Random().nextInt(Samples.getAvailableBooks().size());
    }

}
