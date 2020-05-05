This assignment is made by Michael Malhoe for the Rabobank. It should have taken around three hours but i did not manage to finish it in time.
I am going to explain the trade-offs in this document.

At first, i made the code all by myself without any help except one StackOverflow question. I asked a specific question about the tests which costs me over half an hour to solve myself. Given the time, it was not worth it to spend any more time on it myself.

https://stackoverflow.com/questions/61607182/junit-tests-fail-due-shared-instances-i-think


After around 4-5 hours (including researching stuff i never did before and spending time stuck on parts) i decided to end and commit the code. I could have completed the challenge if i had more time, but this was simply not the case.


The builder pattern would have been perfect in this case, but i did not manage to get it working. Therefore i made my own mapper.

Encapsulation is one of the most basic patterns in Java but i did not manage to get it to work. I assume it has to do with my Lombok not generation getters and setters so i had to make them public to continue. This could have been resolved by having a working environment but i had to do a clean install of my development environment during these e4-5 hours and i believe it was not worth the time to fix this.

In StatementProcessorInputModelTest there is one test ignored. During the debugging i noticed that the converter took the correct input but did not manage to map it into the object. After 30 minutes i decided to continue to the next chapter and fail this part.

The 500 internal server error is not made. Given the specific response required for this assignment i made an design mistake in the beginning which led to problems later on. In hindsight i should have used HTTPResonse instead of making my own object to return.

In the StatementProcessorService i used an arraylist to keep track of duplicate references. This should have been a database but this was out of scope for this assigment.


I had quite some fun with this challenge. It was a time ago i had to make everything from scratch so it was nice to see how far i came.