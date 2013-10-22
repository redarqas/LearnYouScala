foldRight :: (a -> b -> b) -> b -> [a] -> b
foldRight _ z [] = z
foldRight f z (x:xs) = f x (foldRight f z xs) 

foldLeft :: (b -> a -> b) -> b -> [a] -> b
foldLeft _ z [] = z
foldLeft f z (x:xs) = f (foldLeft f z xs) x
