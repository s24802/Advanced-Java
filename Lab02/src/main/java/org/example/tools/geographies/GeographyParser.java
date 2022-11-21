package org.example.tools.geographies;

import org.example.model.Geography;
import org.example.tools.abstractions.IParse;

public class GeographyParser implements IParse<Geography>  {
    public Geography parse(String line)  {
        Geography geography = new Geography();
        String data = line.trim();
        String[] parts = data.split(";");
        geography.setId(Integer.parseInt(parts[0]));
        geography.setName(parts[1]);
        geography.setType(parts[2]);
        geography.setCode(parts[3]);
       if (parts[4].equals("NULL")) {
            geography.setParentId(null);
        }

        return geography;
    }
}

