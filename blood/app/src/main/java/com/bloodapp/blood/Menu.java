package com.bloodapp.blood;

public class Menu {

    private String name;
    private String description;

    public static final Menu[] menus = {

            new Menu("Fever","1) Basil\nTake about 20 basil leaves and boil them, now add 1 teaspoon of crushed ginger in the strained tulsi water, and boil until the solution gets reduced to half. Add a little honey and drink this tea two or three times a day for three days to get relief. \n\n 2)Garlic \nCrush 1 garlic clove and add it to 1 cup of hot water. Let it rest for 10 minutes, and then strain. Drink this twice a day for best results \n\n 3)Ginger \nYou can have it in a form of tea, by adding 1 1/2 teaspoon of grated ginger to 1 cup of boiling water.Add some honey to flavour it, and drink this tea at least three or four times a day.Or you can also mix together 1 1/2 teaspoon of ginger juice, 1 teaspoon of lemon juice and 1 tablespoon of honey. Have this concoction two to three times a day for best results"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating"),
            new Menu("Fever","Basil \n Garlic \n Ginger"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating"),
            new Menu("Fever","Basil \n Garlic \n Ginger"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating"),
            new Menu("Fever","Basil \n Garlic \n Ginger"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating"),
            new Menu("Fever","Basil \n Garlic \n Ginger"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating"),
            new Menu("Fever","Basil \n Garlic \n Ginger"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating"),
            new Menu("Fever","Basil \n Garlic \n Ginger"),
            new Menu("Cold & Cough","Turmeric and Honey \n Drink tea made of Tulsi (Basil leaves), ginger and honey  \n Chew Tulsi leaves"),
            new Menu("Stomach Ace","Drink ghee, salt, and hot water \n Warm water and fennel seeds or ginger \n Eat gourds and keep hydrating")
    };

    private Menu(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String toString()
    {
        return this.name;
    }
}
