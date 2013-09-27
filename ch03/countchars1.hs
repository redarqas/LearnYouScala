import Data.List

main = do
    contents <- getContents
    putStrLn $ formatContent contents

--formatContent :: String -> String
formatContent text = let ls = lines text
                         maxWidth = length $ show $ length $ maximumBy (\f s -> length f `compare` length s) ls 
                     in unlines $ map (\l -> formatLine maxWidth l) ls
formatLine :: Int -> String -> String                     
formatLine w xs = let width = length $ show $ length xs
                      padding = replicate (w-width) ' '
                  in  (show $ length xs)  ++ padding ++ " | " ++ xs   