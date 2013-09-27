
makeIncreaser2 :: Int -> String -> (Int, String)
makeIncreaser2 n s = (n, concat $ replicate n s)

inc1 = makeIncreaser2 2

makeIncreaser :: Int -> String -> (Int, String)
makeIncreaser n = (\s -> (n, concat $ replicate n s))
inc2 = makeIncreaser2 2

