import javax.swing.*;

public class PhotoManager {
    private BST<LinkedList<Photo>> linkedListBST;
    private LinkedList<Photo> allPhotos = new LinkedList<Photo>();

    // Constructor
    public PhotoManager() {
        linkedListBST = new BST<LinkedList<Photo>>();
    }

    // Add a photo
    public void addPhoto(Photo p) {
        if (p != null) {
            if (allPhotos.empty()) {
                allPhotos.insert(p);
            }
            allPhotos.findFirst();
            while (!allPhotos.last()) {
                if (allPhotos.retrieve().getPath().equals(p.getPath())) {
                    return;
                }
                allPhotos.findNext();
            }
            if (!allPhotos.retrieve().getPath().equals(p.getPath())) {
                allPhotos.insert(p);
            }
        }
    }

    // Delete a photo
    public void deletePhoto(String path) {
        Photo photo = findPhoto(path);
        deletePhotoFromList(allPhotos, photo);
        LinkedList<String> pTags = photo.getTags();
        if(!pTags.empty()){
            pTags.findFirst();
            while (!pTags.last()){
                linkedListBST.findKey(pTags.retrieve());
                LinkedList<Photo> tempPhoto = linkedListBST.retrieve();
                deletePhotoFromList(tempPhoto, photo);
                if(linkedListBST.retrieve().empty()){
                    linkedListBST.removeKey(pTags.retrieve());
                }
                pTags.findNext();
            }
            //***************end while****************
            linkedListBST.findKey(pTags.retrieve());
            LinkedList<Photo> tempPhoto = linkedListBST.retrieve();
            deletePhotoFromList(tempPhoto, photo);
            if(linkedListBST.retrieve().empty()){
                linkedListBST.removeKey(pTags.retrieve());
            }
            //***************end while****************
        }
    }

    // Return the inverted index of all managed photos
    public BST<LinkedList<Photo>> getPhotos() {
        linkedListBST = new BST<LinkedList<Photo>>();
        if(!allPhotos.empty()){
            allPhotos.findFirst();
            while (!allPhotos.last()){
                Photo p = allPhotos.retrieve();
                if(!p.getTags().empty()){
                    LinkedList<String> pTags = p.getTags();
                    pTags.findFirst();
                    while (!pTags.last()){
                        if(!linkedListBST.findKey(pTags.retrieve())){
                            LinkedList<Photo> temp = new LinkedList<Photo>();
                            temp.insert(p);
                            linkedListBST.insert(pTags.retrieve(), temp);
                        }else {
                            linkedListBST.retrieve().insert(p);
                        }
                        pTags.findNext();
                    }
                    //**********end of pTags while******************
                    if(!linkedListBST.findKey(pTags.retrieve())){
                        LinkedList<Photo> temp = new LinkedList<Photo>();
                        temp.insert(p);
                        linkedListBST.insert(pTags.retrieve(), temp);
                    }else {
                        linkedListBST.retrieve().insert(p);
                    }
                    //**********end of pTags while******************
                }
                allPhotos.findNext();
            }
            //***********end of allPhotos while************
            Photo p = allPhotos.retrieve();
            if(!p.getTags().empty()){
                LinkedList<String> pTags = p.getTags();
                pTags.findFirst();
                while (!pTags.last()){
                    if(!linkedListBST.findKey(pTags.retrieve())){
                        LinkedList<Photo> temp = new LinkedList<Photo>();
                        temp.insert(p);
                        linkedListBST.insert(pTags.retrieve(), temp);
                    }else {
                        linkedListBST.retrieve().insert(p);
                    }
                    pTags.findNext();
                }
                //**********end of pTags while******************
                if(!linkedListBST.findKey(pTags.retrieve())){
                    LinkedList<Photo> temp = new LinkedList<Photo>();
                    temp.insert(p);
                    linkedListBST.insert(pTags.retrieve(), temp);
                }else {
                    linkedListBST.retrieve().insert(p);
                }
                //**********end of pTags while******************
            }
            //***********end of allPhotos while************
        }
        return linkedListBST;
    }

    public LinkedList<Photo> getAllPhotos() {
        return allPhotos;
    }

    // Private Methods ********************************************
    // Private Methods ********************************************
    // Private Methods ********************************************

    private Photo findPhoto(String path) {
        if (path != null) {
            allPhotos.findFirst();
            while (!allPhotos.last()) {
                if (allPhotos.retrieve().getPath().compareTo(path) == 0) {
                    return allPhotos.retrieve();
                } else
                    allPhotos.findNext();
            }
            //*********************************
            if (allPhotos.retrieve().getPath().compareTo(path) == 0) {
                return allPhotos.retrieve();
                //***********************************
            }
        }
        return null;
    }

    private void deletePhotoFromList(LinkedList<Photo> allPhotos, Photo p){
        if(!allPhotos.empty()){
            allPhotos.findFirst();
            while(!allPhotos.last()){
                if(allPhotos.retrieve().getPath().equals(p.getPath())){
                    allPhotos.remove();
                    return;
                }
                allPhotos.findNext();
            }
            if(allPhotos.retrieve().getPath().equals(p.getPath())){
                allPhotos.remove();
                return;
            }
        }
    }

    private void deleteEmptyData(Photo p) {
        LinkedList<String> pTags = p.getTags();
        if(!pTags.empty()){
            pTags.findFirst();
            while (!pTags.last()){
                linkedListBST.findKey(pTags.retrieve());
                if(linkedListBST.retrieve().empty()){
                    linkedListBST.removeKey(pTags.retrieve());
                }
                pTags.findNext();
            }
            //***************end while****************
            linkedListBST.findKey(pTags.retrieve());
            if(linkedListBST.retrieve().empty()){
                linkedListBST.removeKey(pTags.retrieve());
            }
            //***************end while****************
        }
    }
}