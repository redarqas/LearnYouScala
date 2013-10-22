withDefault :: Maybe Int -> Int
withDefault x = case x of Just y -> y
                          Nothing -> 0

test = do 
   Just x <- [Just 3, Nothing , Just 2, Nothing, Just 5]
   return x                          


