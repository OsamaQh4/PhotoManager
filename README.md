# PhotoManager

### 1. Introduction
> The role of a photo management application is to organize photographs so that they can
be easily accessed. In order to help organize photos, the user can provide tags to describe
the content of the photos. A tag is a keyword associated with a photo. For example, we
can associate the tag ”vacation” to any photo taken during a vacation. In Table 1, you
may find some examples of photos and the tags that are used to describe them.

<img align="center" src="https://github.com/OsamaQh4/PhotoManager/blob/main/Photo1.png?raw=true">

### 2. Inverted index
> <br/>In order to accelerate the search for photos, it is possible to store the tags in an inverted index. The idea is that instead of having the photos point to the tags, the inverted index will store all the tags, and each tag will point to all the photos that contain it.
<br/>The following is an example showing a partial inverted index for the photos shown
<br/>above:
<br/>animal → hedgehog.jpg, bear.jpg, fox.jpg, panda.jpg, wolf.jpg, racoon.jpg
<br/>apple → hedgehog.jpg
<br/>bear → bear.jpg, panda.jpg
<br/>black → butterfly2.jpg
<br/>butterfly → butterfly1.jpg, butterfly2.jpg
<br/>...
