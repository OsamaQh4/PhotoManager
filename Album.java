public class Album {
    private String name;
    private String condition;
    private PhotoManager manager;
    private BST<LinkedList<Photo>> conditions;

    // Constructor
    public Album(String name, String condition, PhotoManager manager) {
        this.name = name;
        this.condition = condition;
        this.manager = manager;
    }

    // Return the name of the album
    public String getName() {
        return name;
    }

    // Return the condition associated with the album
    public String getCondition() {
        return this.condition;
    }

    // Return the manager
    public PhotoManager getManager() {
        return manager;
    }

    // Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos() {
        int countCond = 0;
        int found = 0;
        boolean all = false;
        LinkedList<String> allTags = new LinkedList<String>();
        String [] cond = separateCond();
        LinkedList<Photo> original = new LinkedList<Photo>();
        conditions = manager.getPhotos();
        if(condition.isBlank()){
            return manager.getAllPhotos();
        }
        if(conditions.findKey(cond[countCond])) {
            LinkedList<Photo> temp = conditions.retrieve();
            temp.findFirst();
            while(!temp.last()){
                Photo tempPhoto = temp.retrieve();
                LinkedList<String> tempTags = tempPhoto.getTags();
                tempTags.findFirst();
                while(!tempTags.last()) {
                    for (countCond = 0; countCond < cond.length; ) {
                        if(tempTags.retrieve().compareTo(cond[countCond]) == 0)
                            found++;
                        countCond++;
                    }
                    tempTags.findNext();
                }
                //************end of while tags***************
                for (countCond = 0; countCond < cond.length; ) {
                    if(tempTags.retrieve().compareTo(cond[countCond]) == 0)
                        found++;
                    countCond++;
                }
                //************end of while tags***************
                if(found == cond.length){
                    original.insert(tempPhoto);
                }
                found = 0;
                temp.findNext();
            }
            //*********************end of condition list while***********************************
            Photo tempPhoto = temp.retrieve();
            LinkedList<String> tempTags = tempPhoto.getTags();
            tempTags.findFirst();
            while(!tempTags.last()) {
                for (countCond = 0; countCond < cond.length; ) {
                    if(tempTags.retrieve().compareTo(cond[countCond]) == 0)
                        found++;
                    countCond++;
                }
                tempTags.findNext();
            }
            //************end of while tags***************
            for (countCond = 0; countCond < cond.length; ) {
                if(tempTags.retrieve().compareTo(cond[countCond]) == 0)
                    found++;
                countCond++;
            }
            //************end of while tags***************
            if(found == cond.length){
                original.insert(tempPhoto);
            }
            //*********************end of condition list while***********************************
        }
        return original;
    }

    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps() {
        if(condition.isBlank()){
            return 0;
        }
        return manager.getPhotos().getNbComp(separateCond());
    }


    // Private Methods ********************************************
    // Private Methods ********************************************
    // Private Methods ********************************************


    private String[] separateCond() {
        String[] cond;
        cond = condition.split(" AND ");
        return cond;
    }
}
