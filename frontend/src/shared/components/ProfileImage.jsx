import defaultProfileImage from "@/assets/profile.png";

// eslint-disable-next-line react/prop-types
export function ProfileImage({ width, tempImage, image }) {

  const profileImage = image ? `/assets/profile/${image}` : defaultProfileImage;

  return (
    <img
      src={tempImage || profileImage}
      width={width}
      height={width}
      className="rounded-circle shadow-sm"
      onError={({target}) => {
        target.src = defaultProfileImage
      }}
    />
  );
}