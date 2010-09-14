class Card
{
    public:
        void SetFrontCharacters(char[] characters) { frontChars = characters; }
        void SetBackCharacters(char[] characters) { backChars = characters; }
        void Flip(bool front) { side = front }
    private:
        char frontChars[];
        char backChars[];
        bool side = true;   //When side is true, visible face of card is front
}