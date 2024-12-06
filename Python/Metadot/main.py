import pygame

solves: dict = {"blue": False, "yellow": False}
# yellow = Challenge(pygame.Color("yellow"), lambda: json.load(solves_file)["yellow"])


def main():
    run = True

    pygame.init()
    pygame.display.set_caption("Metadot")
    surface = pygame.display.set_mode((600, 300))
    surface.fill(pygame.Color("darkslategrey"))
    clock = pygame.time.Clock()

    pygame.draw.circle(surface, pygame.Color("blue"), (300, 150), 20, width=5)

    while run:
        for event in pygame.event.get():
            match event:
                case pygame.QUIT:
                    run = False

    pygame.quit()


if __name__ == "__main__":
    main()
